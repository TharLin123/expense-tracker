import React from 'react'
import Details from './components/details/Details';
import {Grid} from '@material-ui/core';
import Main from './components/main/Main';

const App = () => {
    console.log("helolo")
    return (
        <div>
            <Grid container spacing={2} alignItems="center" justify="center" style={{height:'100vh'}}>
                <Grid item xs={false} sm={1}/>
                <Grid item xs={12} sm={3}>
                    <Details title="Income"/>
                </Grid>
                <Grid item xs={12} sm={4}>
                    <Main/>
                </Grid>
                <Grid item xs={12} sm={3}>
                    <Details title="Expense"/>
                </Grid>
                <Grid item xs={false} sm={1}/>
            </Grid> 
        </div>
    )
}

export default App
