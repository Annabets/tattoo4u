package by.bsuir.tattoo4u.service.impl;

import by.bsuir.tattoo4u.dto.response.OrderResponseDto;
import by.bsuir.tattoo4u.entity.Order;
import by.bsuir.tattoo4u.entity.Studio;
import by.bsuir.tattoo4u.entity.User;
import by.bsuir.tattoo4u.repository.OrderRepository;
import by.bsuir.tattoo4u.repository.StudioRepository;
import by.bsuir.tattoo4u.repository.UserRepository;
import by.bsuir.tattoo4u.service.OrderService;
import by.bsuir.tattoo4u.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final StudioRepository studioRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, StudioRepository studioRepository,
                             UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.studioRepository = studioRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void add(Order order) throws ServiceException {
        orderRepository.save(order);
    }

    @Override
    public List<OrderResponseDto> takeUsersOrders(User user) throws ServiceException {
        return compileList(orderRepository.findAllByAuthor(user));
    }

    @Override
    public List<OrderResponseDto> takeStudiosOrders(Long studioId) throws ServiceException {
        Studio studio = studioRepository.getById(studioId);

        List<Order> orders = orderRepository.findAllByStudio(studio);
        return compileList(orders);
    }

    private List<OrderResponseDto> compileList(List<Order> orders) {
        List<OrderResponseDto> list = new LinkedList<>();
        for(Order order : orders) {
            list.add(new OrderResponseDto(order));
        }
        return list;
    }

    @Override
    public void confirmOrder(Long id) throws ServiceException {
        Order order = orderRepository.getById(id);

        order.setFinish(true);
        orderRepository.save(order);
    }
}
