package com.gmail.kulacholeg.task.service;

import com.gmail.kulacholeg.task.model.Group;
import com.gmail.kulacholeg.task.repository.GroupsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class GroupsService {

    private final GroupsRepository repository;

    @Autowired
    public GroupsService(GroupsRepository repository){
        this.repository = repository;
    }

    public Group createGroup(Group group){
        return repository.save(group);
    }

    public List<Group> getAllGroups(){
        return repository.findAll();
    }

    public Group getGroupById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Group not found"
                ));
    }

    public Group updateGroup(Group newGroup, Long id){
        return repository.findById(id)
                .map(group -> {
                    group.setName(newGroup.getName());
                    return repository.save(group);
                })
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Group not found"
                ));
    }

    public void deleteGroup(Long id){
        Group group = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Group not found"
                ));
        repository.delete(group);
    }
}
