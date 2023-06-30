package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
      Image image = new Image();
      image.setDescription(description);
      image.setDimensions(dimensions);

      Blog blog = blogRepository2.findById(blogId).get();
      image.setBlog(blog);

      blog.getImageList().add(image);

      blogRepository2.save(blog);

      return image;
    }

    public void deleteImage(Integer id){
     imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`

        Image image = imageRepository2.findById(id).get();
        String imageDimension = image.getDimensions();

        int idx = imageDimension.indexOf('X');

        String a = imageDimension.substring(0,idx);
        String b = imageDimension.substring(idx+1,imageDimension.length());

        int imageWidth = Integer.parseInt(a);
        int imageLength = Integer.parseInt(b);

        int idxOfScreen = imageDimension.indexOf('X');

        String aOfScreen = imageDimension.substring(0,idxOfScreen);
        String bOfScreen = imageDimension.substring(idxOfScreen+1,screenDimensions.length());

        int screenWidth = Integer.parseInt(aOfScreen);
        int screenLength = Integer.parseInt(bOfScreen);

        int cnt = (screenWidth/imageWidth)*(screenLength/imageLength);

        return cnt;
    }
}
