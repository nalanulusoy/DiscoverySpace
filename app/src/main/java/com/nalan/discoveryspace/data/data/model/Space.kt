package com.nalan.discoveryspace.data.data.model


class Space {
    private var photos: List<Photos>? = null

    fun getPhotos(): List<Photos>? {
        return photos
    }

    fun setPhotos(photos: List<Photos>) {
        this.photos = photos
    }


    class Photos {
        private var sol: String? = null

        private var earth_date: String? = null

        private var id: String? = null

        private var camera: Camera? = null

        private var rover: Rover? = null

        private var img_src: String? = null

        fun getSol(): String? {
            return sol
        }

        fun setSol(sol: String) {
            this.sol = sol
        }

        fun getEarth_date(): String? {
            return earth_date
        }

        fun setEarth_date(earth_date: String) {
            this.earth_date = earth_date
        }

        fun getId(): String? {
            return id
        }

        fun setId(id: String) {
            this.id = id
        }

        fun getCamera(): Camera? {
            return camera
        }

        fun setCamera(camera: Camera) {
            this.camera = camera
        }

        fun getRover(): Rover? {
            return rover
        }

        fun setRover(rover: Rover) {
            this.rover = rover
        }

        fun getImg_src(): String? {
            return img_src
        }

        fun setImg_src(img_src: String) {
            this.img_src = img_src
        }
    }
}