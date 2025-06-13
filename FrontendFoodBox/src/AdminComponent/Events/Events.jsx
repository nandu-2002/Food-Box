import { Box, Button, Grid, Modal, TextField } from "@mui/material";
import React, { useEffect } from "react";
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { DateTimePicker, LocalizationProvider } from "@mui/x-date-pickers";
import dayjs from "dayjs";
import { useDispatch, useSelector } from "react-redux";
import { createEventAction, getRestaurantEvents } from "../../component/State/Restaurant/Action";
import { EventCard } from "../../component/Profile/EventCard";

const style = {
  position: 'absolute',
  top: '50%',
  left: '50%',
  transform: 'translate(-50%, -50%)',
  width: 400,
  bgcolor: 'background.paper',
  border: '2px solid #000',
  boxShadow: 24,
  p: 4,
};

const initialValues = {
  image: [],
  location: "",
  name: "",
  startDateTime: null,
  endDateTime: null,
};

export const Events = () => {
  const [open, setOpen] = React.useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);
  const [formValues, setFormValues] = React.useState(initialValues);

  const dispatch = useDispatch();
  const { restaurant } = useSelector((store) => store);
  const jwt = localStorage.getItem("jwt");

  const handleSubmit = (e) => {
    e.preventDefault();

    // Convert dayjs objects to ISO string format
    const formattedFormValues = {
      ...formValues,
      startDateTime: formValues.startDateTime ? formValues.startDateTime.format('YYYY-MM-DDTHH:mm:ss') : null,
      endDateTime: formValues.endDateTime ? formValues.endDateTime.format('YYYY-MM-DDTHH:mm:ss') : null,
    };

    // Validate before dispatching
    if (!formattedFormValues.name || !formattedFormValues.location || !formattedFormValues.startDateTime || !formattedFormValues.endDateTime) {
      alert("Please fill out all fields.");
      return;
    }

    dispatch(createEventAction({ data: formattedFormValues, restaurantId: restaurant.usersRestaurant?.id, jwt }));
    console.log("submit:", formattedFormValues);
    setFormValues(initialValues);
  };

  const handleFormChange = (e) => {
    const value = e.target.name === 'image' ? [e.target.value] : e.target.value; // Ensure image is an array
    setFormValues({ ...formValues, [e.target.name]: value });
  };

  const handleDateChange = (date, dateType) => {
    setFormValues({ ...formValues, [dateType]: date });
  };

  useEffect(() => {
    dispatch(getRestaurantEvents({ restaurantId: restaurant.usersRestaurant?.id, jwt }));
  }, [dispatch, restaurant.usersRestaurant?.id, jwt]);

  return (
    <div>
      <div className="p-5">
        <Button onClick={handleOpen} variant="contained">Create New Event</Button>
        <Modal
          open={open}
          onClose={handleClose}
          aria-labelledby="modal-modal-title"
          aria-describedby="modal-modal-description"
        >
          <Box sx={style}>
            <form onSubmit={handleSubmit}>
              <Grid container spacing={3}>
                <Grid item xs={12}>
                  <TextField
                    name="image"
                    label="Image URL"
                    variant="outlined"
                    fullWidth
                    value={formValues.image[0] || ''}
                    onChange={handleFormChange}
                  />
                </Grid>
                <Grid item xs={12}>
                  <TextField
                    name="location"
                    label="Location"
                    variant="outlined"
                    fullWidth
                    value={formValues.location}
                    onChange={handleFormChange}
                  />
                </Grid>
                <Grid item xs={12}>
                  <TextField
                    name="name"
                    label="Event Name"
                    variant="outlined"
                    fullWidth
                    value={formValues.name}
                    onChange={handleFormChange}
                  />
                </Grid>
                <Grid item xs={12}>
                  <LocalizationProvider dateAdapter={AdapterDayjs}>
                    <DateTimePicker
                      renderInput={(props) => <TextField {...props} />}
                      label="Start Date and Time"
                      value={formValues.startDateTime}
                      onChange={(newValue) => handleDateChange(newValue, "startDateTime")}
                      inputFormat="MM/dd/yyyy hh:mm a"
                      className="w-full"
                    />
                  </LocalizationProvider>
                </Grid>
                <Grid item xs={12}>
                  <LocalizationProvider dateAdapter={AdapterDayjs}>
                    <DateTimePicker
                      renderInput={(props) => <TextField {...props} />}
                      label="End Date and Time"
                      value={formValues.endDateTime}
                      onChange={(newValue) => handleDateChange(newValue, "endDateTime")}
                      inputFormat="MM/dd/yyyy hh:mm a"
                      className="w-full"
                    />
                  </LocalizationProvider>
                </Grid>
              </Grid>
              <Box mt={2}>
                <Button variant="contained" color="primary" type="submit">Submit</Button>
              </Box>
            </form>
          </Box>
        </Modal>
      </div>
      
      <section className="px-5 lg:px-20 pt-8">
        <h1 className="text-2xl font-semibold text-gray-400 py-5">Events</h1>
        <div className="flex flex-wrap items-center justify-around gap-5">
          {restaurant.restaurantsEvents?.map((item) => (
            <EventCard key={item.id} item={item} />
          ))}
        </div>
      </section>
    </div>
  );
};
