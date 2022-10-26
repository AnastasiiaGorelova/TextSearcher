import React, { useState } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';
import { Container, Paper, Button } from '@material-ui/core';

const useStyles = makeStyles((theme) => ({
  root: {
    '& > *': {
      margin: theme.spacing(1),
      width: '90ch',
    },
  },
}));

export default function Searchbar() {
    const paperStyle={padding:'50px 20px', width:1000, margin:"20px auto"}
    const [input, setInput] = useState('')
    const classes = useStyles();

    const handleClick=(e)=>{
        e.preventDefault()
        console.log(input)
        fetch(`/files/?query=${input}`, {
            method:"GET",
            headers:{"Content-Type":"text/html"}
        }
        ).then(()=>{
            console.log("Go searching")
        })
    }

  return (
    <Container>
        <Paper elevation={3} style={paperStyle}>
            <h1>Enter words separated by spaces or commas</h1>
    <form className={classes.root} noValidate autoComplete="off">
      <TextField id="outlined-basic" label="Search words" variant="outlined" fullWidth 
      value={input}
      onChange={(e)=>setInput(e.target.value)}  //event 
      />
      <Button style={{maxWidth: '170px', maxHeight: '200px'}} variant="contained" color="secondary" onClick={handleClick}>
        Search
      </Button>
    </form>
        </Paper>
    </Container>
  );
}
