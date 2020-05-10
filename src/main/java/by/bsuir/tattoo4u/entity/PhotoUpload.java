package by.bsuir.tattoo4u.entity;

import com.cloudinary.Cloudinary;
import com.cloudinary.StoredFile;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class PhotoUpload extends StoredFile {
    private MultipartFile file;

    public PhotoUpload(MultipartFile file) {
        this.file = file;
    }

    public String getUrl(Cloudinary cloudinary) {
        if (version != null && format != null && publicId != null) {
            return cloudinary.url()
                    .resourceType(resourceType)
                    .type(type)
                    .format(format)
                    .version(version)
                    .generate(publicId);
        } else return null;
    }
}
