package by.bsuir.tattoo4u.service;

import by.bsuir.tattoo4u.dto.response.OrderResponseDto;
import by.bsuir.tattoo4u.entity.Master;
import by.bsuir.tattoo4u.entity.Order;
import by.bsuir.tattoo4u.entity.User;

import java.util.List;

public interface OrderService {
    void add(Order order) throws ServiceException;

    List<OrderResponseDto> takeUsersOrders(User user, Long stdioId) throws ServiceException;

    List<OrderResponseDto> takeStudiosOrders(Long studioId) throws ServiceException;

    void confirmOrder(Long id) throws ServiceException;

    void acceptOrder(Long id, Master master) throws ServiceException;
}
