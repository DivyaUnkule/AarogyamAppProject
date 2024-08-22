package com.app.dto;


public class ExerciseYogaDTO {
    private Long id;
    private String name;
    private String description;
    private Long roleid;


    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRoleid() {
		return roleid;
	}

	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    

	public ExerciseYogaDTO() {
		super();
	}

	public ExerciseYogaDTO(String name, String description, String roleid) {
		super();
		this.name = name;
		this.description = description;
		this.roleid = Long.parseLong(roleid);
	}

	@Override
	public String toString() {
		return "ExerciseYogaDTO [name=" + name + ", description=" + description + "]";
	}

	
    
    
}
