package com.gmail.kulacholeg.task.controller;

import com.gmail.kulacholeg.task.model.Group;
import com.gmail.kulacholeg.task.service.GroupsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GroupsController {

    private final GroupsService groupsService;

    public GroupsController(GroupsService service){
        this.groupsService = service;
    }

    @PostMapping("/groups")
    public Group addGroup(@RequestBody Group group){
        return groupsService.createGroup(group);
    }

    @GetMapping("/groups")
    public List<Group> getAllGroups(){
        return groupsService.getAllGroups();
    }

    @GetMapping("/groups/{id}")
    public Group getGroup(@PathVariable Long id){
        return groupsService.getGroupById(id);
    }

    @PutMapping("/groups/{id}")
    public Group updateGroup(@RequestBody Group group,
                             @PathVariable Long id){
        return groupsService.updateGroup(group, id);
    }

    @DeleteMapping("/groups/{id}")
    public void deleteGroup(@PathVariable Long id){
        groupsService.deleteGroup(id);
    }

}
