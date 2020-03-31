package service;

import domain.Log;
import persistence.LogDAO;
import persistence.impl.LogDAOImpl;

public class LogService {
    Log log;
    private LogDAO logDAO;

    public LogService(){
        log = new Log();
        logDAO = new LogDAOImpl();
    }

    public String logInfo(Object ...s){
        return log.logInfomation(s);
    }

    public void insertLogInfo(String username, String logInfo){
        logDAO.insertLog(username, logInfo);
    }
}
