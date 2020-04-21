package by.bsuir.tattoo4u.service;

import by.bsuir.tattoo4u.entity.Photo;
import by.bsuir.tattoo4u.entity.PhotoUpload;

public interface PhotoService {
    Photo save(PhotoUpload photoUpload) throws ServiceException;
}
