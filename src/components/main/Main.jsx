import React, {useContext} from 'react'
import makeStyles from './Styles';
import {Card, Grid, Divider, CardHeader, CardContent, Typography} from '@material-ui/core';
import Form from './form/Form';
import List from './list/List';
import {ExpenseTrackerContext} from '../../context/context'

const Main = (props) => {
    const classes = makeStyles(props);
    const { transactions , balance} = useContext(ExpenseTrackerContext);

    return (
        <Card className={classes.root}>
            <CardHeader title="Income & Expense Tracker" align="center" subheader="Powered by Me"/>
            <CardContent> 
            <Typography align="center" variant="h5">{`Total Balance $${balance}`}</Typography>
            <Typography variant="subtitle1" style={{ lineHeight: '1.5em', marginTop: '20px'}}>
                Try Saying: Add income for $100 in category slary for Monday ....
            </Typography>

            </CardContent>
            <CardContent>
            <Grid container spacing ={0} direction = "column">
                <Grid item xs={12}>
                <Divider className={classes.divider} />
                <Typography variant="subtitle1" align="center">  
                {transactions.length !== 0 ? `${transactions[0]?.type} of $${transactions[0]?.amount} tracked` : "No transactions detected yet"}
                </Typography>
                    <Form></Form>
                </Grid>
                <Divider className={classes.divider} />
                <Grid item xs={12}>
                    <List/>
                </Grid>
            </Grid> 
            </CardContent>
        </Card>
    )
}

export default Main;
