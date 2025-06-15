```mermaid
classDiagram
    class ItemVO
    class ItemDAO
    class ObjFileHashMapItemDAO
    class ItemService
    class ItemServiceImpl

    ItemDAO <|-- ObjFileHashMapItemDAO
    ItemService <|-- ItemServiceImpl
    ItemServiceImpl --> ItemDAO
```
