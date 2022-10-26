import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';
import { Container, Paper } from '@material-ui/core';

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
    const classes = useStyles();

  return (
    <Container>
        <Paper elevation={3} style={paperStyle}>
    <form className={classes.root} noValidate autoComplete="off">
      <TextField id="outlined-basic" label="Search words" variant="outlined" />
    </form>
        </Paper>
    </Container>
  );
}
