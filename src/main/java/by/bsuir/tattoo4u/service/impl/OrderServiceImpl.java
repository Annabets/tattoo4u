package by.bsuir.tattoo4u.service.impl;

import by.bsuir.tattoo4u.dto.response.OrderResponseDto;
import by.bsuir.tattoo4u.entity.*;
import by.bsuir.tattoo4u.repository.MasterRepository;
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
    private final MasterRepository masterRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, StudioRepository studioRepository,
                            UserRepository userRepository, MasterRepository masterRepository) {
        this.orderRepository = orderRepository;
        this.studioRepository = studioRepository;
        this.userRepository = userRepository;
        this.masterRepository = masterRepository;
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
        for (Order order : orders) {
            list.add(new OrderResponseDto(order));
        }
        return list;
    }

    @Override
    public void confirmOrder(Long id) throws ServiceException {
        Order order = orderRepository.getById(id);

        order.setStatus(OrderStatus.CONFIRMED.toString());
        orderRepository.save(order);
    }

    @Override
    public void acceptOrder(Long id, Master master) throws ServiceException {
        Order order = orderRepository.getById(id);

        order.setStatus(OrderStatus.IN_PROGRESS.toString());
        order.setMaster(master);

        master.getOrders().add(order);
        masterRepository.save(master);
    }
}
