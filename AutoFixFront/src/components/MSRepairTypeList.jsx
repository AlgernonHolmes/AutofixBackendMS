import React, {useState} from 'react';
import repairListService from '../services/repairList.service';
import {TextField, Button, FormControl, MenuItem, Box, Grid} from '@mui/material';

const MSRepairTypeList = () => {

    const [formData, setFormData] = useState({
        repairType: '',
        gasolinePrice: 0.0,
        dieselPrice: 0.0,
        hybridPrice: 0.0,
        electricPrice: 0.0
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
      repairListService.createRepairlist(formData);
      console.log(formData);
      setFormData({
        repairType: '',
        gasolinePrice: 0.0,
        dieselPrice: 0.0,
        hybridPrice: 0.0,
        electricPrice: 0.0
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
                            name="repairType"
                            label="Repair type"
                            value={formData.repairType}
                            onChange={handleChange}
                            required
                            >
                            </TextField>
                    </FormControl>
                </Grid>
                <Grid item xs={6}>
                    <FormControl fullWidth>
                    <TextField
                        name="gasolinePrice"
                        label="Gasoline price"
                        type="number"
                        value={formData.gasolinePrice}
                        onChange={handleChange}
                        required
                    />
                    </FormControl>

                </Grid>
                <Grid item xs={6}>
                    <FormControl fullWidth>
                    <TextField
                        name="dieselPrice"
                        label="Diesel price"
                        type="number"
                        value={formData.dieselPrice}
                        onChange={handleChange}
                        required
                    />
                    </FormControl>

                </Grid>
                <Grid item xs={6}>
                    <FormControl fullWidth>
                    <TextField
                        name="hybridPrice"
                        label="Hybrid price"
                        type="number"
                        value={formData.hybridPrice}
                        onChange={handleChange}
                        required
                    />
                    </FormControl>
                </Grid>
                <Grid item xs={6}>
                    <FormControl fullWidth>
                    <TextField
                        name="electricPrice"
                        label="Electric price"
                        type="number"
                        value={formData.electricPrice}
                        onChange={handleChange}
                        required
                    />
                    </FormControl>
                </Grid>
            </Grid>

        </form>
    );
};

export default MSRepairTypeList;