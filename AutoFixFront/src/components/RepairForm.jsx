import React, {useState, useEffect} from 'react';
import { useParams } from 'react-router-dom';
import repairService from '../services/repair.service';
import repairListService from '../services/repairList.service'
import {TextField, Button, FormControl, MenuItem, Box, Grid} from '@mui/material';



const RepairForm = () => {

    const {registrationPlate} = useParams();

    const [optionsRepair, setOptionsRepair] = useState([]);

    const fetchRepairList = async () => {
      try {
        const response = await repairListService.getAllRepairlists();
        const repairList = response.data;
        const repairTypes = repairList.map(repair => repair.repairType);
        return repairTypes; 
      } catch (error) {
          console.error('Error al obtener la lista de reparaciones:', error);
          return []; 
      }
     };

     useEffect(() => {
      const fetchData = async () => {
          try {
              const repairTypes = await fetchRepairList();
              setOptionsRepair(repairTypes); 
          } catch (error) {
              console.error('Error al obtener la lista de reparaciones:', error);
              setOptionsRepair([]); 
          }
      };

      fetchData(); 
      }, []);



    const [formData, setFormData] = useState({
      vehiclePlate: registrationPlate || '',
      repairType: '',
      repairDate: null, 
      repairTime: null
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
      repairService.createRepairDetail(formData);
      console.log(formData);
      setFormData({
        vehiclePlate: '',
        repairType: '',
        repairDate: null, 
        repairTime: null
      });
    };
    
    

    return (
      <div>
        <h1><b>[add repair detail]</b></h1>
        <form onSubmit={handleSubmit} style={{ backgroundColor: 'white', color: 'black', padding: '20px', borderRadius: '10px' }}>
          <Box mb={2}>
            <Button type="submit" variant="contained" style={{ backgroundColor: '#800000', color: 'white' }}>Submit</Button>
          </Box>
          <Grid container spacing={1}>
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
                {optionsRepair.map((option) => 
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
                  name="repairDate"
                  label="Vehicle repair date"
                  type="date"
                  value={formData.repairDate}
                  onChange={handleChange}
                  InputLabelProps={{
                    shrink: true,
                    placeholder: 'Enter vehicle repair date'
                  }}
                  required
                />
              </FormControl>
            </Grid>
            
            <Grid item xs={6}>
              <FormControl fullWidth>
                <TextField
                  name="repairTime"
                  label="Vehicle repair time"
                  type="time"
                  value={formData.repairTime}
                  onChange={handleChange}
                  InputLabelProps={{
                    shrink: true,
                    placeholder: 'Enter vehicle repair time'
                  }}
                  required
                />
              </FormControl>
            </Grid>

          </Grid>
        </form>
      </div>
    );
    
    

};

export default RepairForm;