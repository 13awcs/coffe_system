package com.coffe_management_system.service.bill;

import com.coffe_management_system.auth.security.JwtTokenUtil;
import com.coffe_management_system.dto.ServerResponseDto;
import com.coffe_management_system.dto.payment.PaymentRequest;
import com.coffe_management_system.dto.table.TableResponseProjection;
import com.coffe_management_system.entity.customer.CustomerEntity;
import com.coffe_management_system.entity.customer.TypeCustomerEntity;
import com.coffe_management_system.entity.order.BillEntity;
import com.coffe_management_system.entity.order.TableEntity;
import com.coffe_management_system.repository.bill.BillRepository;
import com.coffe_management_system.repository.customer.CustomerRepository;
import com.coffe_management_system.repository.customer.TypeCustomerRepository;
import com.coffe_management_system.repository.order.OrderItemRepository;
import com.coffe_management_system.repository.order.TableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class BillService {

    private final BillRepository billRepository;
    private final OrderItemRepository orderItemRepository;
    private final CustomerRepository customerRepository;
    private final TypeCustomerRepository typeCustomerRepository;
    private final TableRepository tableRepository;

    public ServerResponseDto saveBill(PaymentRequest request, String token) {
        JwtTokenUtil j = new JwtTokenUtil();
        Long employeeId = j.getUserId(token);
        Integer finalPrice = orderItemRepository.getFinalPrice(request.getOrderId());
        CustomerEntity customer = customerRepository.findCustomerById(request.getCustomerId());

        TypeCustomerEntity typeCustomer = typeCustomerRepository.getDiscountByCustomerId(customer.getId());
        TableResponseProjection tableFromDB = tableRepository.findByOrderIdAndStatusIsFalse(request.getOrderId(), true);

        BillEntity entity = new BillEntity();

        entity.setOrderId(request.getOrderId());
        entity.setCustomerId(request.getCustomerId());
        entity.setEmployeeId(employeeId);
        entity.setPaymentMethod(request.getPaymentMethod());
        entity.setDiscount(typeCustomer.getTypeCustomer().getValue());
        entity.setFinalPrice((finalPrice - (finalPrice * entity.getDiscount())) + finalPrice * 0.1);
        entity.setCreateTime(new Date());
        billRepository.save(entity);

        TableEntity table = new TableEntity();
        table.setId(tableFromDB.getId());
        table.setName(tableFromDB.getName());
        table.setStatus(!tableFromDB.getIsStatus());
        tableRepository.save(table);

        if(entity.getFinalPrice() >= 50000 && entity.getFinalPrice() < 100000) {
            customer.setPoint(customer.getPoint() + 1);
        } else if(entity.getFinalPrice() >= 100000) {
            customer.setPoint(customer.getPoint() + 2);
        } else if(entity.getFinalPrice() < 50000) {
            customer.setPoint(customer.getPoint());
        }
        customerRepository.save(customer);

        return ServerResponseDto.SUCCESS;
    }

    public ServerResponseDto getListBillByDate(String date) {
        return ServerResponseDto.success(billRepository.getListBillByDate(date));
    }

}
