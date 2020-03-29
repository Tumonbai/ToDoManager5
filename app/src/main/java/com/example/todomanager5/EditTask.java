package com.example.todomanager5;

import java.io.Serializable;
import java.util.Date;

public class EditTask extends Task implements Serializable {

    public String editTitle;
    public String editDescription;
    public Date editStartDate;
    public Date editDeadline;
    public boolean isDone;
    }
