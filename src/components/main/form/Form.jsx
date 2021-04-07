import React,{useState,useContext} from 'react'
import {Grid, Button, FormControl, InputLabel, Select,MenuItem,TextField} from '@material-ui/core';
import makeStyles from './Styles';
import { v4 as uuidv4 } from 'uuid';
import {ExpenseTrackerContext} from '../../../context/context';
import {incomeCategories, expenseCategories} from './categories'
import formatDate from './DateFormat';

const initialState = {
    amount: '',
    category: '',
    type: 'Income',
    date: formatDate(new Date()),
}

const Form = () => {

    const classes = makeStyles();
    const [formData, setFormData] = useState(initialState);
    const { addTransaction } = useContext(ExpenseTrackerContext);
    const selectedCategory = formData.type === "Income" ? incomeCategories : expenseCategories;

    const createTransaction = () => {
        const transaction = { ...formData, amount: Number(formData.amount), id: uuidv4() }
        addTransaction(transaction);
        setFormData(initialState);
    }

    return (
        <Grid container spacing={1}>
            {/* <Grid item xs={12}>
                 <Typography align="center" variant="subtitle2" gutterBottom>
                     Hello World
                 </Typography>
            </Grid> */}
            <Grid item xs={6}>
                 <FormControl fullWidth>
                     <InputLabel>Type</InputLabel>
                     <Select value={formData.type} onChange={(e) => setFormData({...formData, type : e.target.value})}>
                         <MenuItem value="Income">Income</MenuItem>
                         <MenuItem value="Expense">Expense</MenuItem>
                     </Select>
                 </FormControl>
            </Grid>
            <Grid item xs={6}>
                 <FormControl fullWidth>
                     <InputLabel>Category</InputLabel>
                     <Select value={formData.category} onChange={(e) => setFormData({ ...formData, category : e.target.value })}>
                         {selectedCategory.map( categoryItem => (
                             <MenuItem key={categoryItem.type} value={categoryItem.type}>{categoryItem.type}</MenuItem>
                         ))}
                     </Select>
                 </FormControl>
            </Grid>
            <Grid item xs={6}>
                <TextField type="number" label="Amount" fullWidth value={formData.amount} onChange={(e)=> setFormData({...formData, amount : e.target.value})}/>
            </Grid>
            <Grid item xs={6}>
            <TextField fullWidth label="Date" type="date" value={formData.date} onChange={(e) => setFormData({ ...formData, date: formatDate(e.target.value) })} />
            </Grid>
            <Button className={classes.button} variant="outlined" color="primary" fullWidth onClick={createTransaction}>Create</Button>
        </Grid>
    )
}

export default Form
