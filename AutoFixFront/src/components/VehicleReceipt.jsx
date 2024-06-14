import React, { useState } from 'react';
import receiptService from '../services/receipt.service';
import { Button, Paper, Typography, Grid, TextField} from '@mui/material';

const VehicleReceipt = () => {
    const [plate, setPlate] = useState('');
    const [receipt, setReceipt] = useState(null);

    const handleGenerateReceipt = async () => {
        try {
            const response = await receiptService.createReceipt(plate);
            const total_receipt = await receiptService.applyRealPayment(plate);
            setReceipt(total_receipt.data);
        } catch (error) {
            console.error('Error al generar el recibo:', error);
        }
    };

    return (
        <div style={{ backgroundColor: 'white', color: 'black', padding: '20px', borderRadius: '10px' }}>
            <Grid container spacing={2} alignItems="center">
                <Grid item>
                    <TextField
                        type="text"
                        label="Enter vehicle plate"
                        value={plate}
                        onChange={(e) => setPlate(e.target.value)}
                        required
                    />
                </Grid>
                <Grid item>
                    <Button variant="contained" style={{ backgroundColor: '#800000', color: 'white' }} onClick={handleGenerateReceipt}>Generate Receipt</Button>
                </Grid>
            </Grid>
            {receipt && (
                <Paper style={{ padding: '20px', marginTop: '20px' }}>
                    <Typography variant="h6">Receipt</Typography>
                    <Typography><b>Total Payment:</b> {receipt.totalPayment}</Typography>
                    <Typography><b>Total Surcharge:</b> {receipt.totalSurcharge}</Typography>
                    <Typography><b>Total Discount:</b> {receipt.totalDiscount}</Typography>
                    <Typography><b>Coupon Assigned:</b> {receipt.couponAssigned}</Typography>
                    <Typography><b>Real Payment:</b> {receipt.realPayment}</Typography>
                    <Typography><b>Vehicle Plate:</b> {receipt.vehiclePlate}</Typography>
                    <Typography><b>Receipt Date:</b> {receipt.receiptDate}</Typography>
                    <Typography><b>Receipt Time:</b> {receipt.receiptTime}</Typography>
                </Paper>
            )}
        </div>
    );
};

export default VehicleReceipt;

