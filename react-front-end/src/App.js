import './App.css';
import CreateOrUpdateEmployee from './Components/CreateOrUpdateEmployee';
import FooterComponent from './Components/FooterComponent';
import HeaderComponent from './Components/HeaderComponent';
import ListEmployee from './Components/ListEmployee';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom"
import ViewEmployee from './Components/ViewEmployee';

function App() {
  return (
    <>
      <Router>
        <div className="container">
          <HeaderComponent />
          <div className="Box" style={{ marginTop: "4rem", display: 'flex', height: '100%' }}>
            <Routes>
              <Route exact path="/" element={<ListEmployee />} />
              <Route exact path="/add-employee/:id" element={<CreateOrUpdateEmployee />} />
              <Route exact path="/view-employee/:id" element={<ViewEmployee />} />
            </Routes>
          </div>

        </div>
        <FooterComponent />
      </Router>
    </>
  );
}

export default App;
