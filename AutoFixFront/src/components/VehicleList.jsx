import React, {useState, useEffect} from 'react';
import vehicleService from '../services/vehicle.service';
import {Link} from 'react-router-dom';
import {Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, Box, Button} from '@mui/material';

const VehicleList = () => {
  const [vehicles, setVehicles] = useState([]);

  useEffect(() => {
    fetchVehicles();
  }, []);

  const fetchVehicles = async () => {
    try {
      const response = await vehicleService.getAllVehicles();
      setVehicles(response.data);
    } catch (error) {
      console.error('Error al obtener la lista de vehículos:', error);
    }
  };

  const  handleDeletevehicle = (registrationPlate) => {
    vehicleService.deleteVehicleById(registrationPlate);
  }

  return (
    <div>
      <h1><b>[vehicle list]</b></h1>
      <form>
        <Box mb={2}>
          <Button component={Link} to="/add" variant="contained" 
          style={{ backgroundColor: '#800000', color: 'white' }}>
            New vehicle
          </Button>
        </Box>
          
        <TableContainer component={Paper}>
          <Table>
            <TableHead>
              <TableRow>
                <TableCell><b>Registration Plate</b></TableCell>
                <TableCell><b>Brand</b></TableCell>
                <TableCell><b>Model</b></TableCell>
                <TableCell><b>Type</b></TableCell>
                <TableCell><b>Fabrication Year</b></TableCell>
                <TableCell><b>Engine Type</b></TableCell>
                <TableCell><b>Number of Seats</b></TableCell>
                <TableCell><b>Milage</b></TableCell>
                <TableCell><b>Add repair</b></TableCell>
                <TableCell><b>Delete vehicle</b></TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {vehicles.map((vehicle) => (
                <TableRow key={vehicle.registrationPlate}>
                  <TableCell>{vehicle.registrationPlate}</TableCell>
                  <TableCell>{vehicle.brand}</TableCell>
                  <TableCell>{vehicle.model}</TableCell>
                  <TableCell>{vehicle.type}</TableCell>
                  <TableCell>{vehicle.fabricationYear}</TableCell>
                  <TableCell>{vehicle.engineType}</TableCell>
                  <TableCell>{vehicle.numSeats}</TableCell>
                  <TableCell>{vehicle.milage}</TableCell>
                  <TableCell>
                      <Button component={Link} to="/add/repair" variant="contained" style={{ backgroundColor: '#800000', color: 'white' }}>
                        R
                      </Button>
                    </TableCell>
                    <TableCell>
                      <Button component={Link} variant="contained" style={{ backgroundColor: '#800000', color: 'white' }}
                              onClick={() => handleDeletevehicle(vehicle.registrationPlate)}>
                        D
                      </Button>
                    </TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
      </form>
    </div>
  );
};

export default VehicleList;

