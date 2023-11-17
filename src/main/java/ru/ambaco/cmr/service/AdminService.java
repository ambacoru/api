package ru.ambaco.cmr.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ambaco.cmr.entities.User;
import ru.ambaco.cmr.repositories.UserRepository;
import ru.ambaco.cmr.utils.ExcelUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private  final UserRepository userRepository;
    public List<User> getAll(){
        return  userRepository.findAll();
    }

    public ByteArrayInputStream getDataDownloaded() throws IOException {
         List<User> userList = userRepository.findAll();
        ByteArrayInputStream data = ExcelUtils.dataToExcel(userList);
        return data;

    }

}
