import {useContext} from 'react'
import {incomeCategories,expenseCategories,resetCategories} from '../main/form/categories';
import {ExpenseTrackerContext} from '../../context/context'

const useTransactions = (transactionType) => {
    resetCategories();
    const { transactions } = useContext(ExpenseTrackerContext);
    const transactionsPerType = transactions.filter(transactionItem => transactionItem.type === transactionType.title);
    const total = transactionsPerType.reduce((acc, currentValue) => acc += currentValue.amount, 0);
    const categories = transactionType.title === "Income" ? incomeCategories : expenseCategories;

    transactionsPerType.forEach( typeSelectedTransaction => {
        const category = categories.find( categoryItem => categoryItem.type === typeSelectedTransaction.category)
        if(category) category.amount += typeSelectedTransaction.amount;
    })

    const filteredCategory = categories.filter( c => c.amount > 0)

    const chartData = {
        datasets: [{
            data: filteredCategory.map(fc => fc.amount),
            backgroundColor : filteredCategory.map(fc => fc.color)
        }],
        labels: filteredCategory.map(fc => fc.type)
    }

    return { total, chartData }
}

export default useTransactions;