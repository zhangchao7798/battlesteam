package com.medical.exam.common.state;

import com.medical.exam.common.exception.ServiceException;

import java.io.Serializable;
import java.util.Observable;

/**
 * @author xingfudeshi@gmail.com
 * @date 2019/5/22
 * order state machine
 */
public class OrderState extends Observable implements Serializable {
    /**
     * not paid
     */
    public static final int NOT_PAID = 0;
    /**
     * paid
     */
    public static final int PAID = 1;
    /**
     * transport
     */
    public static final int TRANSPORT = 2;

    /**
     * back
     */
    public static final int BACK = 3;
    /**
     * checking
     */
    public static final int CHECKING = 4;
    /**
     * finished
     */
    public static final int FINISHED = 5;
    /**
     * cancelled
     */
    public static final int CANCELLED = 9;
    /**
     * default state
     */
    private int state = NOT_PAID;

    public OrderState() {
    }

    public OrderState(int state) {
        this.state = state;
    }


    private void setState(int state) {
        this.state = state;
        setChanged();
        notifyObservers(this.state);
    }

    /**
     * state name
     *
     * @return
     */
    public String stateName() {
        switch (state) {
            case NOT_PAID:
                return "未支付";
            case PAID:
                return "已支付";
            case TRANSPORT:
                return "运送中";
            case BACK:
                return "回送中";
            case CHECKING:
                return "待检查";
            case FINISHED:
                return "已出报告";
            case CANCELLED:
                return "已取消";
            default:
                return "未知";
        }
    }

    /**
     * state value
     *
     * @return
     */
    public int stateValue() {
        return this.state;
    }

    /**
     * cancel
     * @return
     */
    public int cancel(){
        if(isNotPaid()){
            setState(CANCELLED);
            return CANCELLED;
        }else{
            throw new IllegalStateException("当前状态无法取消.");
        }

    }

    /**
     * take next state
     *
     * @return
     */
    public int next() throws Throwable {
        switch (state) {
            case NOT_PAID:
                setState(PAID);
                return PAID;
            case PAID:
                setState(TRANSPORT);
                return TRANSPORT;
            case TRANSPORT:
                setState(BACK);
                return BACK;
            case BACK:
                setState(CHECKING);
                return CHECKING;
            case CHECKING:
                setState(FINISHED);
                return FINISHED;
            default:
                throw new IllegalStateException("状态异常,无法继续,ref:" + state);
        }
    }

    /**
     * back the state
     *
     * @return
     */
    public int back() throws Throwable {
        switch (state) {
            case FINISHED:
                setState(CHECKING);
                return CHECKING;
            case CHECKING:
                setState(BACK);
                return BACK;
            case BACK:
                setState(TRANSPORT);
                return TRANSPORT;
            case TRANSPORT:
                setState(PAID);
                return PAID;
            case PAID:
                setState(NOT_PAID);
                return NOT_PAID;
            default:
                throw new IllegalStateException("状态异常,无法回退,ref:" + state);
        }
    }

    public boolean isNotPaid() {
        return this.state == NOT_PAID;
    }

    public boolean isPaid() {
        return this.state == PAID;
    }

    public boolean isTransport() {
        return this.state == TRANSPORT;
    }

    public boolean isBack() {
        return this.state == BACK;
    }

    public boolean isChecking() {
        return this.state == CHECKING;
    }

    public boolean isFinished() {
        return this.state == FINISHED;
    }

    public boolean isCancelled() {
        return this.state == CANCELLED;
    }

}
