import React,{ useReducer, createContext } from 'react'
// import List from '../components/main/list/List';
import contextReducer from './ContextReducer';

const initialState = [];

export const ExpenseTrackerContext = createContext(initialState);

const Provider = ({children}) => {
    const [transactions, dispatch] = useReducer(contextReducer, initialState);
    const balance = transactions.reduce((acc, currVal) => (currVal.type === 'Expense' ? acc - currVal.amount : acc + currVal.amount), 0);
    const deleteTransaction = (id) => {
        dispatch({ type: "DELETE_TRANSACTION" , payload : id}) 
    }

    const addTransaction = (transaction) => {
        dispatch({ type: "ADD_TRANSACTION" , payload : transaction}) 
    }

    return (
        <ExpenseTrackerContext.Provider value={{
            addTransaction,
            balance,
            transactions,
            deleteTransaction
        }}>
            {children}  
        </ExpenseTrackerContext.Provider>
    )
}

export default Provider;

