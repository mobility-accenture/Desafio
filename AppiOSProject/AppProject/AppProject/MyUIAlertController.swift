

import Foundation
import UIKit

class MyUIAlertController : UIAlertController {
    
    override var supportedInterfaceOrientations : UIInterfaceOrientationMask {
        return [UIInterfaceOrientationMask.portrait,UIInterfaceOrientationMask.portraitUpsideDown]
    }
    
    override var shouldAutorotate : Bool {
        return false
    }
}
