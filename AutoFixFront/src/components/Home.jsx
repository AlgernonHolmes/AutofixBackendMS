import {Link} from 'react-router-dom';
import {Button, Box} from '@mui/material';


const Home = () => {
    return (
      <div>
        <h1><b>AutoFix</b></h1>
        <br/>
        <h2>Get your vehicle up and Running!</h2>
        <p>
          AutoFix, the best way to repair and mantain your vehicle
          in the best shape possible! <br/>
          This is an implementation build using Springboot for the backend,
          React for the frontend and postgreSQL for the database.
        </p>
        <br/>
        <Box>
          <Button component={Link} to="/list" variant="contained" 
          style={{ backgroundColor: '#800000', color: 'white' }}>
            Get started: Add a new Vehicle!
          </Button>
        </Box>
      </div>
    );
  };
  
  export default Home;