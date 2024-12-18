import SwiftUI
import Shared

struct ContentView: View {
    @State private var showContent = false

    let url = Greeting().url
    @StateObject private var viewModel = ViewModel()



    var body: some View {
        VStack {
            Button("Click me!") {
                withAnimation {
                    showContent = !showContent
                }
            }

            List(viewModel.labels, id: \.self) {
                        Text($0)
                    }


            if showContent {
                VStack(spacing: 16) {

                    AsyncImage(url: URL(string: url))
                    Text("SwiftUI: ")
                }
                .transition(.move(edge: .top).combined(with: .opacity))
            }
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)
        .padding()
        .onAppear {
            Task {
             await viewModel.startObserving()
            }
        }
    }
}

class ViewModel: ObservableObject {
    @Published var labels: [String] = []

     func startObserving() async {
             let flow = Greeting().greet() // Obter o Flow<String> do Kotlin
            for await number in flow {
                    self.labels.append(number)
                }
         }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}

