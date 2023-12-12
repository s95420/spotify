import React from 'react';

const Home = () => {
  const handleExportClick = () => {
    fetch('/backend-export-url')
      .then(response => {
        if (response.ok) {
          console.log('Export successful!');
        } else {
          console.error('Export failed!');
        }
      })
      .catch(error => {
        console.error('Export failed!', error);
      });
  };

  return (
    <div className="container">
      <header className="jumbotron">
        <h3>
          <center>
            Aplikacja prezentująca zestawienie najpopularniejszych artystów oraz
            ich utworów na przestrzeni ostatnich 5 lat
          </center>
        </h3>
        
      </header>
    </div>
  );
};

export default Home;
