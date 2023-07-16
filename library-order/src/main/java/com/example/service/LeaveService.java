package com.example.service;

import org.activiti.engine.history.HistoricDetail;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.util.List;
import java.util.Map;

public interface LeaveService {
    ProcessInstance startProcess(String key, Map<String, Object> variable);
    List<Task> searchTask(ProcessInstance processInstance, String key, String assignee);
    void completeTask(Task task, String assigneeType, String assignee);
    void suspendProcess(ProcessInstance processInstance);
    void activeProcess(ProcessInstance processInstance);
    void rejectTask(Task task, String assigneeType, String assignee);
    List<HistoricDetail> searchHistory(ProcessInstance processInstance);
}
