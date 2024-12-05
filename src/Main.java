import java.util.Scanner;


interface TicketMachineState {
    void selectTicket();
    void insertMoney(double amount);
    void dispenseTicket();
    void cancelTransaction();
}

// Состояние ожидания
class IdleState implements TicketMachineState {
    private TicketMachine machine;

    public IdleState(TicketMachine machine) {
        this.machine = machine;
    }

    @Override
    public void selectTicket() {
        System.out.println("Билет выбран. Внесите деньги.");
        machine.setState(machine.getWaitingForMoneyState());
    }

    @Override
    public void insertMoney(double amount) {
        System.out.println("Сначала выберите билет.");
    }

    @Override
    public void dispenseTicket() {
        System.out.println("Сначала выберите билет.");
    }

    @Override
    public void cancelTransaction() {
        System.out.println("Нечего отменять.");
    }
}


class MoneyReceivedState implements TicketMachineState {
    private TicketMachine machine;

    public MoneyReceivedState(TicketMachine machine) {
        this.machine = machine;
    }

    @Override
    public void selectTicket() {
        System.out.println("Билет уже выбран.");
    }

    @Override
    public void insertMoney(double amount) {
        System.out.println("Деньги уже внесены.");
    }

    @Override
    public void dispenseTicket() {
        System.out.println("Билет выдан. Спасибо за покупку!");
        machine.setState(machine.getTicketDispensedState());
    }

    @Override
    public void cancelTransaction() {
        System.out.println("Транзакция отменена.");
        machine.setState(machine.getIdleState());
    }
}


class TicketDispensedState implements TicketMachineState {
    private TicketMachine machine;

    public TicketDispensedState(TicketMachine machine) {
        this.machine = machine;
    }

    @Override
    public void selectTicket() {
        System.out.println("Процесс завершен. Начните заново.");
    }

    @Override
    public void insertMoney(double amount) {
        System.out.println("Процесс завершен. Начните заново.");
    }

    @Override
    public void dispenseTicket() {
        System.out.println("Билет уже выдан.");
    }

    @Override
    public void cancelTransaction() {
        System.out.println("Транзакция завершена. Нечего отменять.");
    }
}


class TransactionCanceledState implements TicketMachineState {
    private TicketMachine machine;

    public TransactionCanceledState(TicketMachine machine) {
        this.machine = machine;
    }

    @Override
    public void selectTicket() {
        System.out.println("Процесс отменен. Начните заново.");
    }

    @Override
    public void insertMoney(double amount) {
        System.out.println("Процесс отменен. Начните заново.");
    }

    @Override
    public void dispenseTicket() {
        System.out.println("Процесс отменен.");
    }

    @Override
    public void cancelTransaction() {
        System.out.println("Транзакция уже отменена.");
    }
}


class TicketMachine {
    private TicketMachineState idleState;
    private TicketMachineState waitingForMoneyState;
    private TicketMachineState moneyReceivedState;
    private TicketMachineState ticketDispensedState;
    private TicketMachineState transactionCanceledState;

    private TicketMachineState currentState;

    public TicketMachine() {
        idleState = new IdleState(this);
        waitingForMoneyState = new MoneyReceivedState(this);
        moneyReceivedState = new MoneyReceivedState(this);
        ticketDispensedState = new TicketDispensedState(this);
        transactionCanceledState = new TransactionCanceledState(this);

        currentState = idleState;
    }

    public void setState(TicketMachineState state) {
        this.currentState = state;
    }

    public void selectTicket() {
        currentState.selectTicket();
    }

    public void insertMoney(double amount) {
        currentState.insertMoney(amount);
    }

    public void dispenseTicket() {
        currentState.dispenseTicket();
    }

    public void cancelTransaction() {
        currentState.cancelTransaction();
    }

    public TicketMachineState getIdleState() {
        return idleState;
    }

    public TicketMachineState getWaitingForMoneyState() {
        return waitingForMoneyState;
    }

    public TicketMachineState getMoneyReceivedState() {
        return moneyReceivedState;
    }

    public TicketMachineState getTicketDispensedState() {
        return ticketDispensedState;
    }

    public TicketMachineState getTransactionCanceledState() {
        return transactionCanceledState;
    }
}


public class Main {
    public static void main(String[] args) {
        TicketMachine machine = new TicketMachine();

        machine.selectTicket();
        machine.insertMoney(100);
        machine.dispenseTicket();
        machine.cancelTransaction();
    }
}
