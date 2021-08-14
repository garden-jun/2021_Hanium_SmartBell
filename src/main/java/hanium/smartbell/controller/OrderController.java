package hanium.smartbell.controller;

import hanium.smartbell.domain.Order;
import hanium.smartbell.domain.OrderItem;
import hanium.smartbell.service.ItemService;
import hanium.smartbell.service.OrderItemService;
import hanium.smartbell.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final ItemService itemService;
    private final OrderService orderService;
    private final OrderItemService orderItemService;

    @GetMapping(value = "/order")
    public String createOrderForm() {
        return "orders/orderForm";
    }


    @PostMapping(value = "/order")
    public String createOrderItem(@RequestBody OrderItemForm form) {

        orderItemService.orderItem(Long.valueOf(form.getOrderId()),Long.valueOf(form.getItemId()), form.getTemperature(), form.getSize(), form.getAmount(), form.getSizeUp());
        return "orders/orderForm";
    }


    @GetMapping(value = "/orders/orderItemListJson")
    @ResponseBody
    public List<OrderItem> orderItems() {
        List<OrderItem> orderItem = orderItemService.findOrderItems();
        return orderItem;
    }

    @GetMapping(value = "/orders/orderList")
    public String createOrderListForm() {
        return "orders/orderList";
    }

    @PostMapping(value = "/orders/orderList")
    public String createOrderList(@RequestBody OrderListForm form) {

        orderService.order(form.getOrderId());
        return "orders/orderList";
    }


    @GetMapping(value = "/orders/orderStatusListJson")
    @ResponseBody
    public List<Order> orderStatusList() {
        List<Order> orderStatusList = orderService.findOrders();
        return orderStatusList;
    }


    @GetMapping(value = "/orderStatusTable")
    public String createOrderStatusTable(){
        return "orders/orderStatusTable";
    }






//
//
//    /** 제조 완료 */
//    @PostMapping(value = "/orders/{orderId}/completed")
//    public String completeOrder(@PathVariable("orderId") Long orderId) {    //pathvariable : url주소와 맵핑.
//        orderService.completeOrder(orderId);
//        return "/orders/orderList";
//    }
//
//    /** 수령 완료 */
//    @PostMapping(value = "/orders/{orderId}/received")
//    public String receiveOrder(@PathVariable("orderId") Long orderId) {
//        orderService.receiveOrder(orderId);
//        return "/orders/orderList";
//    }

}
