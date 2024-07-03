import React, {useState} from 'react';
import {Link} from 'react-router-dom';
import {List, ListItem, ListItemText,Drawer, Button} from '@mui/material';

const Navbar = () => {
    const [open, setOpen] = useState(false);

    const handleToggle = () => {
        setOpen(!open);
    };

    return (
        <>
            <Button onClick={handleToggle} style={{ backgroundColor: '#800000', color: 'white' , position: 'fixed', top: 10, right: 10 }}>
                Menu
            </Button>
            <Drawer anchor="top" open={open} onClose={handleToggle}>
                <List>
                    <ListItem component={Link} to="/home" onClick={handleToggle}>
                        <ListItemText primary="Home" />
                    </ListItem>
                    <ListItem component={Link} to="/add" onClick={handleToggle}>
                        <ListItemText primary="New vehicle" />
                    </ListItem>
                    <ListItem component={Link} to="/list" onClick={handleToggle}>
                        <ListItemText primary="Vehicle list" />
                    </ListItem>
                    <ListItem component={Link} to="/add/repair" onClick={handleToggle}>
                        <ListItemText primary="New repair" />
                    </ListItem>
                    <ListItem component={Link} to="/list/repair" onClick={handleToggle}>
                        <ListItemText primary="Repair list" />
                    </ListItem>
                    <ListItem component={Link} to="/list/repairhistory" onClick={handleToggle}>
                        <ListItemText primary="Repair history" />
                    </ListItem>
                    <ListItem component={Link} to="/receipt" onClick={handleToggle}>
                        <ListItemText primary="Generate receipt" />
                    </ListItem>
                    <ListItem component={Link} to="/r1" onClick={handleToggle}>
                        <ListItemText primary="R1" />
                    </ListItem>
                    <ListItem component={Link} to="/r2" onClick={handleToggle}>
                        <ListItemText primary="R2" />
                    </ListItem>
                </List>
            </Drawer>
        </>
    );
};

export default Navbar;
