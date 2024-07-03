import React, {useState} from 'react';
import vehicleService from '../services/vehicle.service';
import {TextField, Button, FormControl, MenuItem, Box, Grid} from '@mui/material';


const VehicleForm = () => {

  const [formData, setFormData] = useState({
    registrationPlate: '',
    brand: '',
    model: '',
    type: '',
    fabricationYear: '',
    engineType: '',
    numSeats: '',
    milage: ''
  });
  
  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value
    });
  };
  
  const handleSubmit = (e) => {
    e.preventDefault();
    vehicleService.createVehicle(formData);
    console.log(formData);
    setFormData({
      registrationPlate: '',
      brand: '',
      model: '',
      type: '',
      fabricationYear: '',
      engineType: '',
      numSeats: '',
      milage: ''
    });
  };


  return (
    <div>
      <h1><b>[add a new vehicle]</b></h1>
      <form onSubmit={handleSubmit} style={{ backgroundColor: 'white', 
      color: 'black', padding: '20px', 
      borderRadius: '10px' }}>
        <Box mb={2}>
          <Button type="submit" variant="contained" 
          style={{ backgroundColor: '#800000', color: 'white' }}>Submit
          </Button>
        </Box>

        <Grid container spacing={1}> 
          <Grid item xs={6}>
            <FormControl fullWidth>
              <TextField
                name="registrationPlate"
                label="Registration Plate"
                value={formData.registrationPlate}
                onChange={handleChange}
                required
              />
            </FormControl>
          </Grid>

          <Grid item xs={6}>
            <FormControl fullWidth>
              <TextField
                name="numSeats"
                label="Number of Seats"
                value={formData.numSeats}
                type="number"
                onChange={handleChange}
                required
              />
            </FormControl>
          </Grid>

          <Grid item xs={6}>
            <FormControl fullWidth>
              <TextField
                name="brand"
                label="Brand"
                value={formData.brand}
                onChange={handleChange}
                required
              />
            </FormControl>
          </Grid>

          <Grid item xs={6}>
            <FormControl fullWidth>
              <TextField
                name="milage"
                label="Milage"
                value={formData.milage}
                type="number"
                onChange={handleChange}
                required
              />
            </FormControl>
          </Grid>

          <Grid item xs={6}>
            <FormControl fullWidth>
              <TextField
                name="model"
                label="Model"
                value={formData.model}
                onChange={handleChange}
                required
              />
            </FormControl>
          </Grid>

          <Grid item xs={6}>
            <FormControl fullWidth>
              <TextField
                name="fabricationYear"
                label="Fabrication Year"
                value={formData.fabricationYear}
                type="number"
                onChange={handleChange}
                required
              />
            </FormControl>
          </Grid>

          <Grid item xs={6}>
            <FormControl fullWidth>
              <TextField
                name="type"
                label="Type"
                value={formData.type}
                select
                onChange={handleChange}
                required
              >
                {['Sedan', 'Hatchback', 'SUV', 'Pickup', 'Furgoneta'].map((option) => (
                  <MenuItem key={option} value={option.toLowerCase()}>
                    {option}
                  </MenuItem>
                ))}
              </TextField>
            </FormControl>
          </Grid>

          <Grid item xs={6}>
            <FormControl fullWidth>
              <TextField
                name="engineType"
                label="Engine Type"
                value={formData.engineType}
                select
                onChange={handleChange}
                required
              >
                {['Gasolina', 'Diesel', 'Hibrido', 'Electrico'].map((option) => (
                  <MenuItem key={option} value={option.toLowerCase()}>
                    {option}
                  </MenuItem>
                ))}
              </TextField>
            </FormControl>
          </Grid>
        </Grid>
      </form>
    </div>
    
  );
};

export default VehicleForm;



