package by.bsuir.tattoo4u.service;

import by.bsuir.tattoo4u.dto.response.OrderResponseDto;
import by.bsuir.tattoo4u.entity.Order;
import by.bsuir.tattoo4u.entity.User;

import java.util.List;

public interface OrderService {
    void add(Order order) throws ServiceException;

    List<OrderResponseDto> takeUsersOrders(User user) throws ServiceException;

    List<OrderResponseDto> takeStudiosOrders(Long studioId) throws ServiceException;

    void confirmOrder(Long id) throws ServiceException;
}
