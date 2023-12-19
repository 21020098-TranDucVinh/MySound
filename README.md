# MySound

## Note
- Kotlin
- Mô hình: MVVM
- Hilt: Dependence injection
- Coroutine: Xử lý bất đồng bộ
- Navigation Component: điều hướng giao diện

## Project structure
- Adapter: chứa các adapter cho RecyclerView
- Common: chứa các config, object, constant
- Data: Data layer
    - DataStore: Lưu vào Data Storage (like SharedPreferences)
    - Model: chứa các model
    - Queue: Lưu list bài hát đang phát
    - Repository: cung cấp dữ liệu cho ViewModel
- Di: Dependency injection 
- Extension: chứa các extension function
- MyAPI: chứa các API gọi đến server riêng
- Pagination: chứa các class xử lý phân trang
- Service: chứa các service
- UI: chứa các giao diện. Bao gồm 1 activity và các fragment
- Utils: chứa các util function
- ViewModel: chứa các ViewModel