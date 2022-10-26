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
    const [files, setFiles] = useState([])
    const [isLoading, setIsLoading] = useState(false)
    const classes = useStyles();


    const handleClick = async () => {
        setIsLoading(true);
    
        try {
          const response = await fetch(`/files/?query=${input}`, {
            method: 'GET',
            headers: {
              Accept: 'application/json',
            },
          });
    
          if (!response.ok) {
            throw new Error(`Error! status: ${response.status}`);
          }
    
          const result = await response.json();
    
          console.log('result is: ', JSON.stringify(result, null, 4));
    
          setFiles(result);
        } catch (err) {
            console.log(err.message);
        } finally {
          setIsLoading(false);
        }
      };


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
        <div style={{display: 'flex',  justifyContent:'center', alignItems:'center'}}>
        <h1>Files containing these words</h1>
        </div>
    <Paper elevation={3} style={paperStyle}>

      {files.map(file=>(
        <Paper elevation={6} style={{margin:"10px",padding:"15px", textAlign:"center"}} key={file}>
         {file}
        </Paper>
      ))
    }
    </Paper>
    </Container>
  );
}
