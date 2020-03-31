import React from 'react';
import PropTypes from 'prop-types';
import Modal from './Modal';

class PhotoGrid extends React.Component {
    constructor(props){
        super(props)
        this.handleGetMorePhotos();
        this.handleResize();
    }

    handleGetMorePhotos = () => {
        const {isSearchPage, searchQuery, getMorePhotos} = this.props;
        isSearchPage? getMorePhotos(searchQuery):getMorePhotos();
    }

    renderColumn=(colNum,colLen)=>{
        const {photos} = this.props;
        let columnPhotos = photos.filter((item, index) => {
            return (index % colLen === colNum)
        })
        return columnPhotos.map((photo)=>{
            return(
                <div className="Column-item" key={photo.id} id={photo.id} onClick={this.handleGridItemClick}>
                    <img className="Column-item-img" src={`${photo.photoUrl}`} alt=""/>
                    <div className="Column-item-content">
                        <a>
                            {`${photo.authorName}`}
                        </a>
                    </div>
                </div>
            )
        })
    }

    renderContainerColumns = (columns)=>{
        return columns.map((item,index)=>{
            return(
                <div key={index} className="Grid-column">{this.renderColumn(index,columns.length)}</div>
            )
        })
    }

    handleGridItemClick = (e)=>{
        const {setModalOpenFlag, setModalPhoto, photos} = this.props;
        if(e.target.className === "Column-item-content" || e.target.className === "Column-item-img"){
            setModalPhoto(photos.find((photo)=>{return photo.id === Number(e.currentTarget.id)}));
            setModalOpenFlag(true);
            document.body.style = `overflow-y: hidden`;
        }
    }


    handleResize = ()=>{
        const {columns,setColumns} = this.props;
        const colNum = columns.length;
        const width = window.innerWidth;
        if(width < 625 && colNum!==1)
            setColumns(1)
        else if(width > 625 && width < 1000 && colNum!==2)
            setColumns(2)
        else if(width > 1000 && width < 1500 && colNum!==3)
            setColumns(3)
        else if(width > 1500 && colNum!==4)
            setColumns(4)
    }

    handleScroll = ()=>{
        const {isLoadingPhotos, isUploadFailed} = this.props;
        if (((window.innerHeight * 2 + window.scrollY) >= document.body.scrollHeight) && !isLoadingPhotos && !isUploadFailed) {
            this.handleGetMorePhotos();
        }
    }

    componentDidMount = ()=>{
        window.addEventListener("resize", this.handleResize);
        window.addEventListener("scroll", this.handleScroll);
    }

    componentWillUnmount= ()=>{
        window.removeEventListener("resize", this.handleResize);
        window.removeEventListener("scroll", this.handleScroll);
    }

    render() {
        const {
            columns,
            photos,
            isLoadingPhotos,
            isUploadFailed,
            isModalOpen,
            modalPhoto,
            setModalOpenFlag,
        } = this.props;
        return(
            <>
                <section className="Photo-grid">
                    {photos.length > 0 &&
                    <div className="Grid-container">
                        {this.renderContainerColumns(columns)}
                    </div>}
                    {isLoadingPhotos &&
                    <div className="Loading-indicator">
                        Loading...
                    </div>}
                    {isUploadFailed && <h3>{'Failed to upload photos'}</h3>}
                </section>
                {isModalOpen &&
                <Modal
                    modalPhoto={modalPhoto}
                    setModalOpenFlag={setModalOpenFlag}
                />}
            </>
        )
    }
}

PhotoGrid.propTypes = {
    isLoadingPhotos: PropTypes.bool.isRequired,
    isUploadFailed: PropTypes.bool.isRequired,
    columns: PropTypes.array.isRequired,
    photos: PropTypes.array.isRequired,
    getMorePhotos: PropTypes.func.isRequired,
    modalPhoto: PropTypes.object.isRequired,
    isModalOpen: PropTypes.bool.isRequired,
    setColumns: PropTypes.func.isRequired,
    setModalOpenFlag: PropTypes.func.isRequired,
    setModalPhoto: PropTypes.func.isRequired,
}

export default PhotoGrid;
