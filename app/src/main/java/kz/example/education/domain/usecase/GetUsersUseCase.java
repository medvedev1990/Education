package kz.example.education.domain.usecase;

import java.util.ArrayList;

import kz.example.education.presentation.entities.UserEntity;

public class GetUsersUseCase {

    public ArrayList<UserEntity> initializeUsers(){
        ArrayList<UserEntity> mArrayListUsers = new ArrayList<>();

        for(int i = 0; i < 20; i++){
            UserEntity userEntity = new UserEntity();

            userEntity.setName("John");
            userEntity.setSurname("Wayne");
            userEntity.setAddress("Address");
            userEntity.setCountry("Great Britain");
            userEntity.setFaculty("Engineering");
            userEntity.setGPA(3.3f);
            userEntity.setMark(95);
            userEntity.setUniversity("Royal University");

            if(i%5 == 0){
                userEntity.setName("");
                userEntity.setmBannerImage("https://images.pexels.com/photos/302769/pexels-photo-302769.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500");
                userEntity.setmBannerTitle("Услуги строительной компании!");
            }

            mArrayListUsers.add(userEntity);
        }

        return mArrayListUsers;
    }
}
