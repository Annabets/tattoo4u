package by.bsuir.tattoo4u.controller;

import by.bsuir.tattoo4u.dto.request.OrderRequestDto;
import by.bsuir.tattoo4u.dto.response.OrderResponseDto;
import by.bsuir.tattoo4u.entity.*;
import by.bsuir.tattoo4u.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "api")
public class OrderController {
    private final TokenService tokenService;
    private final StudioService studioService;
    private final UserService userService;
    private final PhotoService photoService;
    private final OrderService orderService;

    @Autowired
    public OrderController(
            StudioService studioService,
            UserService userService,
            TokenService tokenService,
            PhotoService photoService,
            OrderService orderService
    ) {
        this.studioService = studioService;
        this.userService = userService;
        this.tokenService = tokenService;
        this.photoService = photoService;
        this.orderService = orderService;
    }

    @PostMapping("/order")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> addOrder(@RequestHeader("Authorization") String bearerToken,
                                      @ModelAttribute OrderRequestDto requestDto) {
        String username = tokenService.getUsername(tokenService.clearBearerToken(bearerToken));

        Order order = requestDto.getOrder();
        try {
            User user = userService.getByUsername(username);

            PhotoUpload photoUpload = new PhotoUpload(requestDto.getFile());
            Photo photo = photoService.save(photoUpload);

            order.setPhoto(photo);
            order.setAuthor(user);
            order.setStudio(studioService.takeStudioById(requestDto.getStudioId()));

            user.getOrders().add(order);

            orderService.add(order);
        } catch (ServiceException ex) {
            throw new ControllerException(ex);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/orders")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> takeAllUsersOrders(@RequestHeader("Authorization") String bearerToken) {
        String username = tokenService.getUsername(tokenService.clearBearerToken(bearerToken));
        User user = userService.getByUsername(username);

        List<OrderResponseDto> orders;
        try {
            orders = orderService.takeUsersOrders(user);
        } catch (ServiceException ex) {
            throw new ControllerException(ex);
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/studiosOrders")
    @PreAuthorize("hasAuthority('MASTER')")
    public ResponseEntity<?> takeAllStudiosOrders(@RequestHeader("Authorization") String bearerToken,
                                                  @RequestParam Long studioId) {
        List<OrderResponseDto> orders;
        try {
            orders = orderService.takeStudiosOrders(studioId);
        } catch (ServiceException ex) {
            throw new ControllerException(ex);
        }
        return new ResponseEntity<>(orders, HttpStatus.CREATED);
    }

    @PostMapping("/confirmationOrder")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> confirmOrder(@RequestParam Long id) {
        try {
            orderService.confirmOrder(id);
        } catch (ServiceException ex) {
            throw new ControllerException(ex);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/acceptance")
    @PreAuthorize("hasAuthority('MASTER')")
    public ResponseEntity<?> acceptOrder(@RequestHeader("Authorization") String bearerToken,
                                        @RequestParam Long id ) {
        try {
            String username = tokenService.getUsername(tokenService.clearBearerToken(bearerToken));
            Master master = userService.getByUsername(username).getMasterInfo();

            orderService.acceptOrder(id, master);
        } catch (ServiceException ex) {
            throw new ControllerException(ex);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
