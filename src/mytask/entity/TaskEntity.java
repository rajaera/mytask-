/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytask.entity;



/**
 *
 * @author Eranga
 */
public class TaskEntity {
    public long id;
    public String task;
    public boolean isDone;
    public String taskCreatedAt;
    public String taskCompletedAt;
    public int createdBy;

    @Override
    public boolean equals(Object o) {
        if(o == this) {
            return true;
        }
        
        if(!(o instanceof TaskEntity)) {
            return false;
        }
        
        TaskEntity te = (TaskEntity)o;
        
        if(task.equals(te.task) && isDone == te.isDone) {
            return true;
        }
        
        return false;
        
    }
    
    
    
}
