import httpClient from "../http-common";

const getAllVehicles = () => {
    return httpClient.get('/ms/vehicle');
}

const getVehicleByPlate = (registrationPlate) => {
    return httpClient.get(`/ms/vehicle/${registrationPlate}`);
}

const createVehicle = (vehicle) => {
    return httpClient.post("/ms/vehicle", vehicle);
}

const updateVehicleById = (id, updatedVehicle) => {
    return httpClient.put(`/ms/vehicle/${id}`, updatedVehicle);
}

const updateVehicle = (updatedVehicle) => {
    return httpClient.put("/ms/vehicle", updatedVehicle);
}

const deleteVehicleById = (id) => {
    return httpClient.delete(`/ms/vehicle/${id}`);
}

const deleteVehicleByPlate = (plate) => {
    return httpClient.delete(`/ms/vehicle/${plate}`);
}

export default { 
    getAllVehicles, 
    getVehicleByPlate, 
    createVehicle, 
    updateVehicleById, 
    updateVehicle, 
    deleteVehicleById, 
    deleteVehicleByPlate 
};

