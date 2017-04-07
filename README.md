 
 
 	class ResearchDirectionIndexDTO {
	
		private String des;

		private String desEn;

		private Integer level;

		private Boolean isLeaf;

		private String parentId;

		private List<ResearchDirectionIndexDTO> children;
	}
 
 
	/**
     * 转树形结构
     * @param parent
     * @param list
     */
    public void addChildFromList(final ResearchDirectionIndexDTO parent, List<ResearchDirectionIndexDTO> list) {
	
        Collection<ResearchDirectionIndexDTO> children = Collections2.filter(list, new Predicate<ResearchDirectionIndexDTO>() {
            @Override
            public boolean apply(ResearchDirectionIndexDTO input) {
                return input.getParentId() == parent.getId() || (input.getParentId() != null && input.getParentId().equals(parent.getId()));
            }
        });

        for (ResearchDirectionIndexDTO node : children) {
            if (parent.getChildren() == null) {
                parent.setChildren(new ArrayList<ResearchDirectionIndexDTO>());
            }
            parent.getChildren().add(node);
            addChildFromList(node, list);
        }
    }
	

   

