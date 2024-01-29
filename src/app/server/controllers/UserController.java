package app.server.controllers;

import app.dto.LoginDTO;
import app.dto.RegisterDTO;
import app.server.Response;
import app.server.Response.ResponseBuilder;
import app.server.Response.StatusCode;
import services.json.JsonSerializer;
import dataaccess.repositories.UsersRepository;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Optional;
import models.authorizable.UserPojo;

public class UserController {
    UsersRepository usersRepository;
    BufferedWriter serverWriter;
    BufferedReader serverReader;
    //Logger logger;
    JsonSerializer serializer;

    public UserController(UsersRepository repository, BufferedWriter writer, BufferedReader reader,
        JsonSerializer serializer) {
        this.usersRepository = repository;
        this.serverWriter = writer;
        this.serverReader = reader;
        this.serializer = serializer;
        //this.logger = (Logger) App.getServiceLocator().getService("logger");
    }

    public void registerUser(String requestData) throws IOException {
        System.out.println("Received REGISTER request: " + requestData);

        ResponseBuilder responseBuilder = new ResponseBuilder();

        RegisterDTO registerDTO = serializer.deserialize(requestData, RegisterDTO.class);
        Optional<UserPojo> queryResult = usersRepository.findByName(registerDTO.username);

        if(queryResult.isPresent()){
            responseBuilder.withStatusCode(StatusCode.CONFLICT);
            responseBuilder.withResponseData("User with this username already exists");
        }
        else {
            UserPojo userDataToRegister = new UserPojo(registerDTO.username,registerDTO.password,
                registerDTO.dateOfBirth, registerDTO.joiningDate);

            usersRepository.insert(userDataToRegister);

            responseBuilder.withStatusCode(StatusCode.CREATED);
            responseBuilder.withResponseData(serializer.serialize(userDataToRegister));
        }

        serverWriter.write(serializer.serialize(responseBuilder.build()));
        serverWriter.flush();
    }

    public void loginUser(String requestData) throws IOException {
        System.out.println("Receiver LOGIN request: " + requestData);
        ResponseBuilder responseBuilder = new ResponseBuilder();

        try {
            LoginDTO userDTO = serializer.deserialize(requestData, LoginDTO.class);
            Optional<UserPojo> queryResult = usersRepository.findByName(userDTO.username);

            responseBuilder.withStatusCode(StatusCode.UNAUTHORIZED);
            responseBuilder.withResponseData("Wrong username or password");

            if (queryResult.isPresent()) {
                UserPojo userFromQuery = queryResult.get();

                if (userDTO.password.equals(userFromQuery.getPassword())) {
                    responseBuilder.withStatusCode(StatusCode.OK);
                    responseBuilder.withResponseData(serializer.serialize(userFromQuery));
                }
            }

        } catch (Exception e) {
            responseBuilder.withStatusCode(StatusCode.INTERNAL_SERVER_ERROR);
            responseBuilder.withResponseData("Internal server error");
        }

        serverWriter.write(serializer.serialize(responseBuilder.build()));
        serverWriter.flush();
    }



}
