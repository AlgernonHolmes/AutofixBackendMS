import React, {useState} from 'react';
import {Link} from 'react-router-dom';
import repairService from '../services/repair.service';
import {Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, Box, Button, TextField, Grid} from '@mui/material';

const RepairList = () => {
    const [vehiclePlate, setVehiclePlate] = useState('');
    const [repairs, setRepairs] = useState([]);
    const [exitCDate, setExitCDate] = useState('');
    const [exitCTime, setExitCTime] = useState('');

    const handleChange = (e) => {
        setVehiclePlate(e.target.value);
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await repairService.getRepairsByPlate(vehiclePlate);
            setRepairs(response.data);
        } catch (error) {
            console.error('Error al obtener las reparaciones:', error);
        }
    };

    const handleUpdateRepair = async (repairId) => {
        try {
            const repairToUpdate = repairs.find(repair => repair.id === repairId);
            const updatedRepair = { ...repairToUpdate, exitCDate: exitCDate, exitCTime: exitCTime || new Date().toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' }) };
            await repairService.updateRepair(updatedRepair);
            const updatedRepairs = repairs.map(repair => {
                if (repair.id === repairId) {
                    return { ...repair, exitCDate: exitCDate, exitCTime: exitCTime || new Date().toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' }) };
                }
                return repair;
            });
            setRepairs(updatedRepairs);
        } catch (error) {
            console.error('Error al actualizar la reparación:', error);
        }
    };

    return (
        <div style={{ backgroundColor: 'white', color: 'black', padding: '20px', borderRadius: '10px' }}>
            <form onSubmit={handleSubmit} mt={2} >
                <Grid container spacing={2} alignItems="center">
                    <Grid item>
                        <TextField
                            name="vehiclePlate"
                            label="Vehicle Plate"
                            value={vehiclePlate}
                            onChange={handleChange}
                            required
                        />
                    </Grid>
                    <Grid item>
                        <Button type="submit" variant="contained" style={{ backgroundColor: '#800000', color: 'white' }}>Search</Button>
                    </Grid>
                </Grid>
            </form>

            <TableContainer component={Paper}>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell><b>Exit Vehicle Date</b></TableCell>
                            <TableCell><b>Exit Customer Date</b></TableCell>
                            <TableCell><b>Exit Customer Time</b></TableCell>
                            <TableCell><b>Enter Customer Exit Date</b></TableCell>
                            <TableCell><b>Update Repair</b></TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {repairs.map((repair) => (
                            <TableRow key={repair.id}>
                                <TableCell>{repair.exitVDate || "No date available"}</TableCell>
                                <TableCell>{repair.exitCDate || "No date available"}</TableCell>
                                <TableCell>{repair.exitCTime || "No time available"}</TableCell>
                                <TableCell>
                                    <TextField
                                        label="Customer Exit Date"
                                        type="date"
                                        value={exitCDate}
                                        onChange={(e) => setExitCDate(e.target.value)}
                                        InputLabelProps={{
                                            shrink: true,
                                        }}
                                        required
                                    />
                                    <TextField
                                        label="Customer Exit Time"
                                        type="time"
                                        value={exitCTime || new Date().toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' })}
                                        onChange={(e) => setExitCTime(e.target.value)}
                                        InputLabelProps={{
                                            shrink: true,
                                        }}
                                        required
                                    />
                                </TableCell>
                                <TableCell>
                                    <Button variant="contained" style={{ backgroundColor: '#800000', color: 'white' }} onClick={() => handleUpdateRepair(repair.id)}>Update</Button>
                                </TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>

            <Box mt={2}>
                <Button component={Link} to="/add/repair" variant="contained" style={{ backgroundColor: '#800000', color: 'white' }}>Add Repair</Button>
            </Box>

            <Box mt={2}>
                <Button component={Link} to="/receipt" variant="contained" style={{ backgroundColor: '#800000', color: 'white' }}>Generate Receipt</Button>
            </Box>
        </div>
    );
};

export default RepairList;






