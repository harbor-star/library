package com.example.service.impl;

import com.example.service.LeaveService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricDetail;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 陈磊
 * @version 2.0
 * @date 2023/5/17 20:58
 */
@Service
public class LeaveServiceImpl implements LeaveService {
    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;

    @Override
    public ProcessInstance startProcess(String key, Map<String, Object> variable) {
        return runtimeService.startProcessInstanceByKey(key, variable);
    }

    @Override
    public List<Task> searchTask(ProcessInstance processInstance, String key, String assignee) {
        return taskService.createTaskQuery().taskAssignee(assignee).processInstanceId(processInstance.getId()).list();
    }

    @Override
    public void completeTask(Task task, String assigneeType, String assignee) {
        Map<String, Object> variable = new HashMap<>();
        variable.put("state_"+assigneeType, 1);
        taskService.complete(task.getId(), variable);
    }

    @Override
    public void suspendProcess(ProcessInstance processInstance) {
        if (!processInstance.isSuspended())
            runtimeService.suspendProcessInstanceById(processInstance.getId());
    }

    @Override
    public void activeProcess(ProcessInstance processInstance) {
        if (processInstance.isSuspended())
        runtimeService.activateProcessInstanceById(processInstance.getId());
    }

    @Override
    public void rejectTask(Task task, String assigneeType, String assignee) {
        Map<String, Object> variable = new HashMap<>();
        variable.put("state_"+assigneeType, 0);
        taskService.complete(task.getId(), variable);
    }

    @Override
    public List<HistoricDetail> searchHistory(ProcessInstance processInstance) {
        return historyService.createHistoricDetailQuery().activityInstanceId(processInstance.getId()).list();
    }
}
