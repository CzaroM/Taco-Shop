package tacos.tacoshop.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tacos.tacoshop.Order;

@RequiredArgsConstructor
@RequestMapping(path="/orders",
        produces="application/json")
@RestController
public class OrderController {

    private final OrderService orderService;

    @GetMapping(produces="application/json")
    public Iterable<Order> allOrders() {
        return orderService.allOrders();
    }

    @PostMapping(consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Order postOrder(@RequestBody Order order) {
        return orderService.postOrder(order);
    }

    @PutMapping(path="/{orderId}", consumes="application/json")
    public Order putOrder(@RequestBody Order order) {
        return orderService.putOrder(order);
    }

    @PatchMapping(path="/{orderId}", consumes="application/json")
    public Order patchOrder(@PathVariable("orderId") int orderId,
                            @RequestBody Order patch) {
        return orderService.patchOrder(orderId,patch);
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("orderId") int orderId) {
        orderService.deleteOrder(orderId);
    }

}
