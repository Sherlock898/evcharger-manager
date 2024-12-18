const Dashboard = ({ role }) => {
    if (role === 'admin') {
        return <h2>Bienvenido, Admin</h2>;
    }

    return <Map />;
};

export default Dashboard;
