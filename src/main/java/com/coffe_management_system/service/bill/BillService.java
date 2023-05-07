package com.coffe_management_system.service.bill;

import com.coffe_management_system.auth.security.JwtTokenUtil;
import com.coffe_management_system.dto.ServerResponseDto;
import com.coffe_management_system.dto.payment.PaymentRequest;
import com.coffe_management_system.dto.table.TableResponseProjection;
import com.coffe_management_system.entity.customer.CustomerEntity;
import com.coffe_management_system.entity.customer.TypeCustomerEntity;
import com.coffe_management_system.entity.order.BillEntity;
import com.coffe_management_system.entity.order.OrderEntity;
import com.coffe_management_system.entity.order.TableEntity;
import com.coffe_management_system.repository.bill.BillRepository;
import com.coffe_management_system.repository.customer.CustomerRepository;
import com.coffe_management_system.repository.customer.TypeCustomerRepository;
import com.coffe_management_system.repository.order.OrderItemRepository;
import com.coffe_management_system.repository.order.OrderRepository;
import com.coffe_management_system.repository.order.TableRepository;
import com.coffe_management_system.service.customer.TypeCustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BillService {

    private final BillRepository billRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final TypeCustomerRepository typeCustomerRepository;
    private final TableRepository tableRepository;
    private final TypeCustomerService typeCustomerService;

    public ServerResponseDto saveBill(Long storeId, PaymentRequest request) {
        BillEntity bill = new BillEntity();
        Long employeeId = request.getEmployeeId();
        Long customerId = request.getCustomerId();
        Long tableId = request.getTableId();
        Long orderId = orderItemRepository.getMaxOrderIdByTableId(tableId);
        System.err.println(orderId);
        CustomerEntity customer;
        customer = customerRepository.findCustomerById(customerId);
        if (customerId == null) {
            bill.setCustomerId(null);
            bill.setDiscount(0.0);
        } else {
            bill.setCustomerId(request.getCustomerId());
            TypeCustomerEntity typeCustomer = typeCustomerRepository.getDiscountByCustomerId(customerId);
            bill.setDiscount(typeCustomer.getTypeCustomer().getValue());
        }

        Integer finalPrice = orderItemRepository.getFinalPrice(orderId);
        bill.setOrderId(orderId);
        bill.setEmployeeId(employeeId);
        bill.setStoreId(storeId);
        bill.setPaymentMethod(request.getPaymentMethod());
        bill.setFinalPrice((finalPrice - (finalPrice * bill.getDiscount())) + finalPrice * 0.1);
        bill.setCreateTime(new Date());
        billRepository.save(bill);

        Optional<OrderEntity> orderOpt = orderRepository.findById(orderId);
        OrderEntity order = orderOpt.get();
        if (orderOpt.isEmpty()) {
            return ServerResponseDto.ERROR;
        }
        order.setPaid(!order.isPaid());
        orderRepository.save(order);

        TableResponseProjection tableFromDB = tableRepository.findByStoreIdAndOrderIdAndStatusIsFalse(storeId, orderId, true);
        System.err.println(tableFromDB);
        TableEntity table = new TableEntity();
        table.setId(tableFromDB.getId());
        table.setName(tableFromDB.getName());
        table.setStoreId(tableFromDB.getStoreId());
        table.setStatus(!tableFromDB.isStatus());
        tableRepository.save(table);

        if (customerId != null) {
            if(bill.getFinalPrice() >= 50000 && bill.getFinalPrice() < 100000) {
                customer.setPoint(customer.getPoint() + 1);
            } else if(bill.getFinalPrice() >= 100000) {
                customer.setPoint(customer.getPoint() + 2);
            } else if(bill.getFinalPrice() < 50000) {
                customer.setPoint(customer.getPoint());
            }
            customerRepository.save(customer);
            typeCustomerService.saveTypeCustomer(customer.getId());
        }
        return ServerResponseDto.SUCCESS;
    }

    public ServerResponseDto getListBillByDate(Long storeId, String date) {
        return ServerResponseDto.success(billRepository.getListBillByDate(storeId, date));
    }

}
