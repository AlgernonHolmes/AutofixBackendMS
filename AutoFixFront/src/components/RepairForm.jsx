import React, {useState} from 'react';
import repairService from '../services/repair.service';
import vehicleService from '../services/vehicle.service';
import {TextField, Button, FormControl, MenuItem, Box, Grid} from '@mui/material';



const RepairForm = () => {

    const [formData, setFormData] = useState({
        entryVDate: null,
        entryVTime: null,
        repairType: '',
        totalCost: 0.0,
        exitVDate: null,
        exitCDate: null,
        exitVTime: null,
        exitCTime: null,
        vehiclePlate: ''
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
      repairService.createRepair(formData);
      console.log(formData);
      setFormData({
        entryVDate: null,
        entryVTime: null,
        repairType: '',
        totalCost: 0.0,
        exitVDate: null,
        exitCDate: null,
        exitVTime: null,
        exitCTime: null,
        vehiclePlate: ''
      });
    };
  
    return (
      <form onSubmit={handleSubmit} style={{ backgroundColor: 'white', color: 'black', padding: '20px', borderRadius: '10px' }}>
        <Box mb={2}>
          <Button type="submit" variant="contained" style={{ backgroundColor: '#800000', color: 'white' }}>Submit</Button>
        </Box>
        <Grid container spacing={1}>
          <Grid item xs={6}>
            <FormControl fullWidth>
              <TextField
                name="entryVDate"
                label="Vehicle entry date"
                type="date"
                value={formData.entryVDate}
                onChange={handleChange}
                InputLabelProps={{
                  shrink: true,
                  placeholder: 'Enter vehicle entry date'
                }}
                required
              />
            </FormControl>
          </Grid>
          <Grid item xs={6}>
            <FormControl fullWidth>
              <TextField
                name="entryVTime"
                label="Vehicle entry time"
                type="time"
                value={formData.entryVTime}
                onChange={handleChange}
                InputLabelProps={{
                  shrink: true,
                  placeholder: 'Enter vehicle entry time'
                }}
                required
              />
            </FormControl>
          </Grid>
          <Grid item xs={6}>
            <FormControl fullWidth>
              <TextField
                name="repairType"
                label="Repair type"
                value={formData.repairType}
                select
                onChange={handleChange}
                required
              >
              {["Frenos","Refrigeracion","Motor","Transmision","Electrico",
              "Escape","Neumaticos y Ruedas","Suspension y la Dirección",
              "Aire Acondicionado y Calefaccion","Combustible","Parabrisas y Cristales"].map((option) => 
              (
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
                name="exitVDate"
                label="Vehicle exit date"
                type="date"
                value={formData.exitVDate}
                onChange={handleChange}
                InputLabelProps={{
                  shrink: true,
                  placeholder: 'Enter vehicle exit date'
                }}
                required
              />
            </FormControl>
          </Grid>
          <Grid item xs={6}>
            <FormControl fullWidth>
              <TextField
                name="exitVTime"
                label="Vehicle exit time"
                type="time"
                value={formData.exitVTime || new Date(new Date().getTime() + 4 * 60 * 60 * 1000).toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' })}
                onChange={handleChange}
                required
              />
            </FormControl>
          </Grid>
          <Grid item xs={6}>
            <FormControl fullWidth>
              <TextField
                name="vehiclePlate"
                label="Vehicle plate"
                value={formData.vehiclePlate}
                onChange={handleChange}
                required
              />
            </FormControl>
          </Grid>
        </Grid>
      </form>
    );
    
    

};

export default RepairForm;