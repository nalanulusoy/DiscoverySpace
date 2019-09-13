package com.nalan.discoveryspace.data.data.model

class Cameras {
    private var full_name: String? = null

    private var name: String? = null

    private var id: String? = null

    private var rover_id: String? = null

    fun getFull_name(): String? {
        return full_name
    }

    fun setFull_name(full_name: String) {
        this.full_name = full_name
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getId(): String? {
        return id
    }

    fun setId(id: String) {
        this.id = id
    }

    fun getRover_id(): String? {
        return rover_id
    }

    fun setRover_id(rover_id: String) {
        this.rover_id = rover_id
    }



}
