package whiteheadcrab.springframework.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import whiteheadcrab.springframework.domain.Recipe;
import whiteheadcrab.springframework.repositories.RecipeRepositories;

import java.io.IOException;

@Service
@Slf4j
public class ImageServiceImpl implements ImageService
{
    private final RecipeRepositories recipeRepositories;

    public ImageServiceImpl(RecipeRepositories recipeRepositories)
    {
        this.recipeRepositories = recipeRepositories;
    }

    @Transactional
    @Override
    public void saveImageFile(Long recipeId, MultipartFile file) {
        log.debug("Received a file");

        try
        {
            Recipe recipe = recipeRepositories.findById(recipeId).get();

            Byte[] byteObjects = new Byte[file.getBytes().length];

            int i = 0;

            for (byte b : file.getBytes())
            {
                byteObjects[i++] = b ;
            }

            recipe.setImage(byteObjects);

            recipeRepositories.save(recipe);
        }
        catch (IOException e)
        {
            log.error("Error occupied",e);
            e.printStackTrace();
        }
    }
}
